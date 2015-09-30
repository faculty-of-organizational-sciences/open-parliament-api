package rs.otvoreniparlament.api.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import rs.otvoreniparlament.api.util.exceptions.KeyNotFoundInBundleException;

public class ResourceBundleUtil {

	private static Map<String, ResourceBundle> bundles = null;

	private static final String MESSAGES_BUNDLE = "status_messages";

	private ResourceBundleUtil() {
		bundles = new HashMap<String, ResourceBundle>();
	}

	public static String getString(String path, String key, Object... params) throws KeyNotFoundInBundleException {
		if (bundles == null) {
			bundles = new HashMap<String, ResourceBundle>();
		}

		ResourceBundle bundle = null;
		if (bundles.containsKey(path)) {
			bundle = bundles.get(path);
		} else {
			bundle = ResourceBundle.getBundle(path);
			bundles.put(path, bundle);
		}
		String value = getString(key, bundle, params);

		if (value != null) {
			return value;
		} else {
			throw new KeyNotFoundInBundleException("Key '" + key + "' is not found in the bundle '" + path + "'");
		}
	}

	public static String getMessage(String key, Object... params) throws KeyNotFoundInBundleException {
		return getString(MESSAGES_BUNDLE, key, params);
	}

	public static String getString(String key, ResourceBundle bundle, Object... params) {
		if (bundle.containsKey(key)) {
			return MessageFormat.format(bundle.getString(key), params);
		} else {
			return null;
		}
	}

}
