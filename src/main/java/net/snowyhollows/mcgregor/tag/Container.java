/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.mcgregor.tag;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author efildre
 */
public interface Container extends Component {
	List<Component> getChildren();
	Component setChildren(List<Component> children);
	default void visit(Consumer<Component> consumer) {
		consumer.accept(this);
		if (getChildren() != null) {
			getChildren().forEach(c -> c.visit(consumer));
		}
	}

	default boolean areAllChildrenIdentifiableOnClient() {
		return getChildren().stream().allMatch(c -> c.isIdentifiableOnClient());
	}
}
