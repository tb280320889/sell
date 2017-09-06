package com.github.sherlock.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
//@EnableAutoConfiguration(exclude = ElasticsearchAutoConfiguration.class)
public class SellApplication {

//  private final Environment env;
//
//  @Autowired
//  public SellApplication(Environment env) {
//    this.env = env;
//  }

  public static void main(String[] args) {
    SpringApplication.run(SellApplication.class, args);
  }

//  /**
//   * @return
//   * @throws IOException
//   */
//  @Bean(destroyMethod = "shutdown")
//  public RedissonClient redissonClient() throws IOException {
//    String[] profiles = env.getActiveProfiles();
//    String profile = "";
//    if (profiles.length > 0) {
//      profile = "-" + profiles[0];
//    }
//    return Redisson.create(
//      Config.fromYAML(new ClassPathResource("redisson" + profile + ".yml").getInputStream())
//    );
//  }

}
