package be.howest.jwt.o2.util;

import be.howest.jwt.o2.domain.entities.User;
import be.howest.jwt.o2.ex.CoreException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Hayk
 */
public class PasswordBuilder {

    private static final String SALT_KEY = "-IKr%%P&*_PpN$#n";

    private static byte[] getSalt() {
        return SALT_KEY.getBytes(Charset.forName("UTF-8"));
    }

    public String createHashedPassword(String realPassword)
            throws CoreException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] salt = getSalt();
            md.update(salt);
            byte[] bytes = md.digest(realPassword.getBytes(Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new CoreException(ex);
        }
    }
    
    public boolean passwordsMatch(String realPassword, String securePassword)
        throws CoreException {
        String hashedPassword = createHashedPassword(realPassword);
        return securePassword.equals(hashedPassword);
    }

}
