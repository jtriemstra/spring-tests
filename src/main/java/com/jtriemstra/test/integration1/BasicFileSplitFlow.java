package com.jtriemstra.test.integration1;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
public class BasicFileSplitFlow {
	Logger logger = LoggerFactory.getLogger("BasicFileSplitFlow");
	
	//Does this only do one message per 10sec, even if more are available?
	@Bean
	public IntegrationFlow fileSplit() {
		IntegrationFlow flow = IntegrationFlows.from(sourceDirectory(), c -> c.poller(Pollers.fixedDelay(10000)))
				.log(LoggingHandler.Level.INFO)  
				.handle(log())
				.handle(log())
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
	public MessageHandler log() {
		return (m -> logger.info("log statement"));
	}
}
