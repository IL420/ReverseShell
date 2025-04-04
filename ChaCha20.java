package obs1d1anc1ph3r.reverseshell.client.encryption;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class ChaCha20 {

	private static final int NONCE_SIZE = 12;
	private static final int KEY_SIZE = 32;
	
	//Encrypt the ChaCha
	public static byte[] encrypt(byte[] key, byte[] nonce, byte[] message) throws Exception {
		if (key.length != KEY_SIZE || nonce.length != NONCE_SIZE) {
			throw new IllegalArgumentException("Invalid key or nonce length");
		}

		Cipher cipher = Cipher.getInstance("ChaCha20");
		ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonce, 1);
		SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");

		cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
		return cipher.doFinal(message);
	}

	//Decrypt the ChaCha
	public static byte[] decrypt(byte[] key, byte[] nonce, byte[] ciphertext) throws Exception {
		if (key.length != KEY_SIZE || nonce.length != NONCE_SIZE) {
			throw new IllegalArgumentException("Invalid key or nonce length");
		}

		Cipher cipher = Cipher.getInstance("ChaCha20");
		ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonce, 1);
		SecretKeySpec keySpec = new SecretKeySpec(key, "ChaCha20");

		cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
		return cipher.doFinal(ciphertext);
	}

	//Isn't a Nonce like the stupid hat or something? -- Future me here, no, it is not, you're a fucking dunce.
	//This is just a util, could probably move it elsewhere
	public static byte[] generateNonce() {
		byte[] nonce = new byte[NONCE_SIZE];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	//This is just a util, could probably move it elsewhere
	public static byte[] stringToBytes(String str) {
		return str.getBytes(StandardCharsets.UTF_8);
	}

	//This is just a util, could probably move it elsewhere
	public static String bytesToString(byte[] bytes) {
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
