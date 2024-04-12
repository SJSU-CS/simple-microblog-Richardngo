package edu.sjsu.cmpe272.simpleblog.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.FileSystemResource;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Command
public class ClientApplication implements CommandLineRunner, ExitCodeGenerator
{

    private String rootUrl = "http://localhost:8080";
    private String iniFileLocation = "./user.ini";

    @Autowired
    CommandLine.IFactory iFactory;

    @Autowired
    private ConfigurableApplicationContext context;

    private UserConfig conf;

    @Command
    public int post(@Parameters String message, @Parameters(defaultValue = "null") String attachment) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        //targeted endpoint
        String urlEndPoint = "/messages/create";

        //load in the current user conf if none exist throw error and exit
        loadConfigFile();

        //get current date and time
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss-SSSZ");
        String currentTimeStamp = now.format(formatter);

        //set current date
        Messages newMessage = new Messages(currentTimeStamp, conf.getUserName(),message);

        if (!attachment.equals("null"))
        {
            File inputFile = new File(attachment);
            String encodedAttachment = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(inputFile));

            newMessage.setAttachment(encodedAttachment);
        }

        PrivateKey privateKey = conf.getWorkingPrivateKey();
        newMessage.generateSignature(privateKey);

        //sending request to server
        RestTemplate restTemplate = new RestTemplate();
        String responsePost = restTemplate.postForObject(this.rootUrl+urlEndPoint,newMessage, String.class);

        return 2;
    }

    @Command
    int create(@Parameters String id) throws NoSuchAlgorithmException, IOException
    {
        //targeted endpoint
        String urlEndPoint = "/user/create";

        //check if userID has only lowercase and numbers
        if(!id.matches("[a-z0-9]*"))
        { throw new InvalidUserIDException(id); }

        //create a public-private key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keys = keyGen.generateKeyPair();

        //encoding the public key
        String based64PublicKey = Base64.getEncoder().encodeToString(keys.getPublic().getEncoded());
        Users creator = new Users(id,based64PublicKey);

        //encoding the private key
        String based64PrivateKey = Base64.getEncoder().encodeToString(keys.getPrivate().getEncoded());

        //creating INI file
        FileWriter iniWriter = new FileWriter(iniFileLocation);
        ObjectMapper mapping = new ObjectMapper();
        UserConfig conf = new UserConfig(id,based64PrivateKey);
        iniWriter.write(mapping.writerWithDefaultPrettyPrinter().writeValueAsString(conf));
        iniWriter.close();

        //sending request to server
        RestTemplate restTemplate = new RestTemplate();
        String responsePost = restTemplate.postForObject(this.rootUrl+urlEndPoint,creator, String.class);

        System.out.println(responsePost);

        return 2;
    }

    private void loadConfigFile() throws IOException
    {
        conf = new ObjectMapper().readValue(new FileSystemResource(iniFileLocation).getFile(), UserConfig.class);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(ClientApplication.class, args);
    }

    int exitCode;

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(this, iFactory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

}
