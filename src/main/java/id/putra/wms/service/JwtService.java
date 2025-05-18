package id.putra.wms.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final ResourceLoader resourceLoader;


    public String generateToken(String username) {
        try {
            return Jwts.builder().signWith(privateKey()).subject(username).compact();
        } catch (InvalidKeySpecException | IOException | NoSuchAlgorithmException e) {
            throw new JwtException(e.getMessage(), e);
        }
    }

    public Jws<Claims> claimsJws(String token) {
        try {
            return Jwts.parser().verifyWith(publicKey()).build().parseSignedClaims(token);
        } catch (InvalidKeySpecException | IOException | NoSuchAlgorithmException e) {
            throw new JwtException(e.getMessage(), e);
        }
    }


    private PrivateKey privateKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("Ed25519");

        try (FileReader fr = new FileReader(resourceLoader.getResource("classpath:private.pem").getFile()); PemReader pr = new PemReader(fr)) {
            PemObject pemObject = pr.readPemObject();
            byte[] content = pemObject.getContent();

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(content, BouncyCastleProvider.PROVIDER_NAME);
            return kf.generatePrivate(keySpec);
        }
    }

    private PublicKey publicKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("Ed25519");

        try (FileReader fr = new FileReader(resourceLoader.getResource("classpath:public.pem").getFile());
             PemReader pemReader = new PemReader(fr)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return factory.generatePublic(pubKeySpec);
        }
    }
}
