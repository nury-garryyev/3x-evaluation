package io.mend.sast.service;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpService.class);

    public FTPClient loginFtp(String host, int port, String username, String password) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host, port);
        ftpClient.login(username, password);
        return ftpClient;
    }

    public void printTree(String path, FTPClient ftpClient) throws Exception {
        for (FTPFile ftpFile : ftpClient.listFiles(path)) {
            logger.debug(String.format("[printTree][%d]\n", System.currentTimeMillis()));
            logger.debug(String.format("[printTree][%d] Get name : %s \n", System.currentTimeMillis(), ftpFile.getName()));
            logger.debug(String.format("[printTree][%d] Get timestamp : %s \n", System.currentTimeMillis(), ftpFile.getTimestamp().getTimeInMillis()));
            logger.debug(String.format("[printTree][%d] Get group : %s \n", System.currentTimeMillis(), ftpFile.getGroup()));
            logger.debug(String.format("[printTree][%d] Get link : %s \n", System.currentTimeMillis(), ftpFile.getLink()));
            logger.debug(String.format("[printTree][%d] Get user : %s \n", System.currentTimeMillis(), ftpFile.getUser()));
            logger.debug(String.format("[printTree][%d] Get type : %s \n", System.currentTimeMillis(), ftpFile.getType()));
            logger.debug(String.format("[printTree][%d] Is file : %s \n", System.currentTimeMillis(), ftpFile.isFile()));
            logger.debug(String.format("[printTree][%d] Is directory : %s \n", System.currentTimeMillis(), ftpFile.isDirectory()));
            logger.debug(String.format("[printTree][%d] Formatted string : %s \n", System.currentTimeMillis(), ftpFile.toFormattedString()));

            if (ftpFile.isDirectory()) {
                printTree(path + File.separator + ftpFile.getName(), ftpClient);
            }
        }
    }
}
