package com.myretail.product.model;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product_detail")
@Getter
@Setter
public class ProductDetails {

	/**
     * This is the primary column of the table which represents product id of Integer type and this cannot be NULL.
     *
     * ex: 13860428
     */
	@NotNull
    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private Integer id;

    /**
     * This String type field contains current price and currency code of the item.
     *
     * ex: {"value":15.49,"currency_code":"USD"}
     */
    @Column("current_price")
    private String currentPrice;
}
