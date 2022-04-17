
package com.example.demo;

	import com.amazonaws.AmazonClientException;
	import com.amazonaws.AmazonServiceException;
	import com.amazonaws.services.s3.AmazonS3;
	import com.amazonaws.services.s3.model.*;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.stereotype.Service;

	import java.io.ByteArrayOutputStream;
	import java.io.IOException;
	import java.io.InputStream;

	@Service
	public class downloadFile {

	    private Logger logger = LoggerFactory.getLogger(downloadFile.class);

	    @Autowired
	    private AmazonS3 amazonS3Client;

	    @Value("${application.bucket.name}")
	    private String bucketName;


	    /**
	     * Downloads file using amazon S3 client from S3 bucket
	     *
	     * @param keyName
	     * @return ByteArrayOutputStream
	     */
	    public ByteArrayOutputStream downloadFileFunction(String keyName) {
	        try {
	            S3Object s3object = amazonS3Client.getObject(new GetObjectRequest(bucketName, keyName));

	            InputStream is = s3object.getObjectContent();
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            int len;
	            byte[] buffer = new byte[4096];
	            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
	                outputStream.write(buffer, 0, len);
	            }

	            return outputStream;
	        } catch (IOException ioException) {
	            logger.error("IOException: " + ioException.getMessage());
	        } catch (AmazonServiceException serviceException) {
	            logger.info("AmazonServiceException Message:    " + serviceException.getMessage());
	            throw serviceException;
	        } catch (AmazonClientException clientException) {
	            logger.info("AmazonClientException Message: " + clientException.getMessage());
	            throw clientException;
	        }

	        return null;
	    }
	}

