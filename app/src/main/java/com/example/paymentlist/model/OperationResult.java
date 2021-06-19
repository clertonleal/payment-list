/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.example.paymentlist.model;

import java.net.URL;
import java.util.Map;




/**
 * This class is designed to hold information about operation result.
 */

public class OperationResult {
    /** PCI API, optional */
    private Map<String, URL> links;
    /** PCI API, always present */
    private String resultInfo;
    /** PCI API, optional, always present in response to action (POST, UPDATE) */
    private Interaction interaction;
    /** PCI API, optional */
    private Redirect redirect;
    /** Provider response parameters. */
    private ProviderParameters providerResponse;
}
