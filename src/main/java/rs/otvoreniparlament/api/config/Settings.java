package rs.otvoreniparlament.api.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Settings {

	private final Logger logger = LogManager.getLogger(Settings.class);

	private static final String configFileName = "config.json";

	private static final String defaultConfigFilePath = "config/";

	private static String absoluteConfigPath = System.getenv("OPEN_PARLIAMENT") + File.separator + ".parlament"
			+ File.separator;

	public Config config;

	private Gson gson;

	private static class SettingsHolder {
		private static final Settings INSTANCE = new Settings();
	}

	public static Settings getInstance() {
		return SettingsHolder.INSTANCE;
	}

	private Settings() {
		gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			loadConfig();
		} catch (Exception e) {
			logger.error("Could not load settings: ", e);
		}
	}

	private void loadConfig() throws Exception {

		if (System.getenv("OPEN_PARLIAMENT") != null) {
			absoluteConfigPath = System.getenv("OPEN_PARLIAMENT") + File.separator + ".parlament" + File.separator;
			logger.info("Used OPEN_DATA variable.");
		} else {
			absoluteConfigPath = System.getProperty("user.home") + File.separator + ".parlament" + File.separator;
			logger.info("Used user home variable. OPEN_PARLIAMENT is not set.");
		}

		String homeConfigFile = absoluteConfigPath + configFileName;

		try {
			File homeConfig = new File(homeConfigFile);
			// if there is a config file in the /.parlament folder
			// load that one
			if (homeConfig.exists()) {
				FileReader reader = new FileReader(homeConfigFile);
				config = gson.fromJson(reader, Config.class);
			}
			// otherwise, load the default config file
			else {
				loadDefaultConfig();
				if (config != null) {
					// and save it to the <USER_HOME>/.parlament folder
					saveConfig();
				} else {
					throw new Exception();
				}
			}
			// logger.debug("user: " + config.dbConfig.user);
			// logger.info("Settings loaded!");
			// logger.error("test");

		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException(
					"Could not open the configuration file: " + configFileName + " - " + fnfe.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Could not serialize the configuration file: " + configFileName, e);
		}
	}

	private void loadDefaultConfig() throws Exception {
		FileReader reader = null;
		try {
			// get path to config file
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(defaultConfigFilePath + configFileName);
			// if (url != null) {
			String path = url.getFile();
			// remove white spaces encoded with %20
			path = path.replaceAll("%20", " ");
			reader = new FileReader(path);
			config = gson.fromJson(reader, Config.class);
			// } else {
			// loadDefaultConfig1(is, serializer);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Could not read the config file: " + configFileName, e);
		} finally {
			if (null != reader) {

				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Could not close InputStream!", e);
				}
			}
		}
	}

	private void saveConfig() {
		logger.info("Saving settings...");
		String homeConfigFile = absoluteConfigPath + configFileName;
		File source = new File(homeConfigFile);

		// create dir
		new File(absoluteConfigPath).mkdirs();

		try {
			source.createNewFile();
			FileWriter writer = new FileWriter(homeConfigFile);
			String configString = gson.toJson(config);

			logger.debug(configString);

			writer.write(configString);
			writer.close();

			logger.info("Settings saved!");
		} catch (Exception e) {
			logger.error("Could not save the configuration file: " + configFileName, e);
		}
	}

	//
	// private void loadDefaultConfig1(InputStream is, Serializer serializer)
	// throws FileNotFoundException {
	// is =
	// Thread.currentThread().getContextClassLoader().getResourceAsStream(defaultConfigFilePath
	// + configFileName);
	//
	// if (is != null) {
	// try {
	// config = serializer.read(Config.class, is);
	// } catch (Exception e) {
	// logger.error("Could not read the config file: " + configFileName, e);
	// }
	// } else {
	// throw new FileNotFoundException("Could not open the config file: " +
	// configFileName);
	// }
	// }
	//
	// public String getAbsoluteConfigPath(){
	// return absoluteConfigPath;
	// }

}