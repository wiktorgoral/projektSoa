import java.security.MessageDigest;

import org.jboss.security.Base64Encoder;

import javax.xml.bind.DatatypeConverter;


public class Hash {
    public static void main(String[] args) throws Exception {
        String password = "admin";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printBase64Binary(digest);
        System.out.println(myHash);
    }
}

