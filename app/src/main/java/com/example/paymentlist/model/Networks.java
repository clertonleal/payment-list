/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.example.paymentlist.model;

import java.util.Date;
import java.util.List;




/**
 * This class is designed to hold list of applicable and registered payment network descriptions.
 */

public class Networks {
    /** Simple API, always present */
    private List<ApplicableNetwork> applicable;
    /** Simple API, always present */
    private Date resourcesLastUpdate;

    public List<ApplicableNetwork> getApplicable() {
        return applicable;
    }
}
