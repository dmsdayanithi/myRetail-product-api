package com.myretail.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

/**
 * This class defines the CassandraClusterFactoryBean.
 *
 * @author Dayanithi Devarajan
 */
@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace.name}")
	private String keySpace;

	@Value("${spring.data.cassandra.contact.points}")
	private String contactPoint;

	@Value("${spring.data.cassandra.port}")
	private int portNumber;

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(contactPoint);
		cluster.setPort(portNumber);
		cluster.setJmxReportingEnabled(false);
		return cluster;
	}
}
