package com.jtriemstra.test.integration1;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageHandler;

//@Configuration
public class FileCopyFlow {
	
	//This does not appear to load the entire file into memory
	@Bean
	public IntegrationFlow fileCopy() {
		IntegrationFlow flow = IntegrationFlows.from(sourceDirectory(), c -> c.poller(Pollers.fixedDelay(10000)))
				  //.filter(onlyJpgs())
				.log(LoggingHandler.Level.INFO)  
				.handle(targetDirectory())
				  // add more components
				  .get();
		
		return flow;
	}
	
	@Bean
	public MessageSource<File> sourceDirectory() {
	    FileReadingMessageSource messageSource = new FileReadingMessageSource();
	    messageSource.setDirectory(new File("c:\\temp\\input"));
	    return messageSource;
	}
	
	
	
	@Bean
	public MessageHandler targetDirectory() {
	    FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("c:\\temp\\output"));
	    handler.setFileExistsMode(FileExistsMode.REPLACE);
	    handler.setExpectReply(false);
	    return handler;
	}
}
