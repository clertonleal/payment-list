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
 * Describes a collection of provider specific parameters.
 */

public class ProviderParameters {
    /** optional, provider code. */
    private String providerCode;
    /** collection of parameters. */
    private List<Parameter> parameters;
}
