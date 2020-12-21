package com.myretail.product.model;

import com.datastax.driver.core.DataType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("product_detail")
@Getter
@Setter
public class ProductDetails {

    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private Integer id;

    @Column("current_price")
    private String currentPrice;
}
