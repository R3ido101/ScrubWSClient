package me.r3ido101.ScrubClient;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ChatroomClientEndpoint {
    //SnakeYML Content

    public static org.slf4j.Logger logger	= LoggerFactory.getLogger(ChatroomClientEndpoint.class);
    public static Map<String, Object> conf = null;
    public static File configurationFile	= new File("./Config.yml");


    public static void setupFolders() {
        logger.info("Begin reading the configuration");
        File directory = configurationFile.getParentFile();
        if (!directory.exists()) directory.mkdirs();

        if (!configurationFile.exists()) {
            logger.info("Config file doesn't exists , attempting to create it");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(ChatroomClientEndpoint.class.getClassLoader().getResourceAsStream("BotLogin.yml")));
                FileWriter writer = new FileWriter(configurationFile);

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line + "\n");
                }
                writer.flush();
                writer.close();
                logger.info("I've Finished Writing your Default Config!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            logger.info("Config file exists");
            Yaml yaml = new Yaml(new Representer(), new DumperOptions());
            Map<String, Object> load = (Map<String, Object>)yaml.load(new FileInputStream(configurationFile));
            conf = load;
            logger.info("Managed to load config file");
        } catch (Throwable t) {
            logger.error("Config file load failed");
            conf = new HashMap<String, Object>();
            t.printStackTrace();
        }
    }

    private static String WSCURI = ("Passing this!");

    public ChatroomClientEndpoint() throws URISyntaxException
    {
        URI uRI = new URI(WSCURI);
    }

    @OnOpen
    public void processOpen(Session session)
    {

    }

    @OnMessage
    public void processMessage(String message)
    {

    }
}
