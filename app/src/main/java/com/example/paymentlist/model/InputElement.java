/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.example.paymentlist.model;

import java.util.List;




/**
 * Form input element description.
 */

public class InputElement {
    /** name */
    private String name;
    /** type */
    private String type;
    private String value;
    /** options */
    private List<SelectOption> options;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
