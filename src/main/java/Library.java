import org.springframework.core.SpringVersion;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {
    public boolean someLibraryMethod() {
        return true;
    }
    public static void main(String[] args) {
    	System.out.println(org.hibernate.Version.getVersionString());
    	System.out.println(SpringVersion.getVersion());
	}
}
