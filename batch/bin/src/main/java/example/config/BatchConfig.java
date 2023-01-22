package example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import example.listener.JobCompletionListener;

import example.model.*;
import example.repository.*;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DogRepository dogRepository;

	//Writer class Object
	@Bean
	public <T, U extends JpaRepository<T, Long>> ItemWriter<T> writer(String str, U repository){
		 // return new DogItemWriter(); // Using lambda expression code instead of a separate implementation
		 return records -> {
			 //System.out.println("Saving "+str+" Records: " +records);
			 repository.saveAll(records);
		 };
	}

	//Reader class Object
	@Bean
	public <T> FlatFileItemReader<T> reader(String file, Class<T> c) {

		 FlatFileItemReader<T> reader= new FlatFileItemReader<>();
		 reader.setResource(new ClassPathResource("/"+file+".csv"));
		 reader.setLineMapper(new DefaultLineMapper<>() {{
				 setLineTokenizer(new DelimitedLineTokenizer() {{
						setDelimiter(DELIMITER_COMMA);
						setNames("id", "breed", "color");
				 }});

				 setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
						setTargetType(c);
				 }});
		 }});
		 return reader;
	}

	//Step Object
	@Bean
	public Step stepA() {
		 return stepBuilderFactory.get("stepA")
						 .<Dog,Dog>chunk(5000)
						 .reader(reader("dog", Dog.class))
						 .writer(writer("Dog", dogRepository))
						 .build();
	}

	//Job Object
	@Bean
	public Job jobA(){
		 return jobBuilderFactory.get("jobA")
						.incrementer(new RunIdIncrementer())
						.listener(listener())
						.start(stepA())
						.build();
	}
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
