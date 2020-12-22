package com.myretail.product.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class defines the ProductDescription of an Item.
 *
 * @author Dayanithi Devarajan
 */
@Getter
@Setter
public class ProductDescription {

	/**
     * This is String type title of the product.
     *
     * ex: "SpongeBob SquarePants: SpongeBob's Frozen Face-off"
     */
    private String title;
}
