package selenium.utils

/**
 * Created by kenichiro_ota on 2014/01/22.
 */
class Platform {
    static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows")
    }
}
