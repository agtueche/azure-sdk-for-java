/**
 * Copyright Microsoft Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.windowsazure.services.serviceBus.models;

import javax.ws.rs.core.MediaType;

import com.microsoft.windowsazure.services.serviceBus.implementation.Content;
import com.microsoft.windowsazure.services.serviceBus.implementation.CorrelationFilter;
import com.microsoft.windowsazure.services.serviceBus.implementation.EmptyRuleAction;
import com.microsoft.windowsazure.services.serviceBus.implementation.Entry;
import com.microsoft.windowsazure.services.serviceBus.implementation.EntryModel;
import com.microsoft.windowsazure.services.serviceBus.implementation.FalseFilter;
import com.microsoft.windowsazure.services.serviceBus.implementation.Filter;
import com.microsoft.windowsazure.services.serviceBus.implementation.RuleAction;
import com.microsoft.windowsazure.services.serviceBus.implementation.RuleDescription;
import com.microsoft.windowsazure.services.serviceBus.implementation.SqlFilter;
import com.microsoft.windowsazure.services.serviceBus.implementation.SqlRuleAction;
import com.microsoft.windowsazure.services.serviceBus.implementation.TrueFilter;

/**
 * Represents a rule.
 */
public class RuleInfo extends EntryModel<RuleDescription> {

    /**
     * Creates an instance of the <code>Rule</code> class.
     */
    public RuleInfo() {
        super(new Entry(), new RuleDescription());
        getEntry().setContent(new Content());
        getEntry().getContent().setType(MediaType.APPLICATION_XML);
        getEntry().getContent().setRuleDescription(getModel());
    }

    /**
     * Creates an instance of the <code>Rule</code> class using the specified entry.
     * 
     * @param entry
     *            An <code>Entry</code> object.
     * 
     */
    public RuleInfo(Entry entry) {
        super(entry, entry.getContent().getRuleDescription());
    }

    /**
     * Creates an instance of the <code>Rule</code> class using the specified name.
     * 
     * @param name
     *            A <code>String</code> object that represents the name of the rule.
     * 
     */
    public RuleInfo(String name) {
        this();
        setName(name);
    }

    /**
     * Returns the name of the rule.
     * 
     * @return A <code>String</code> object that represents the name of the rule.
     */
    public String getName() {
        return getEntry().getTitle();
    }

    /**
     * Sets the name of the rule.
     * 
     * @param value
     *            A <code>String</code> object that represents the name of the rule.
     * 
     * @return A <code>Rule</code> object that represents the updated rule.
     */
    public RuleInfo setName(String value) {
        getEntry().setTitle(value);
        return this;
    }

    /**
     * Returns the filter used for the rule.
     * 
     * @return A <code>Filter</code> object that represents the filter of the rule.
     */
    public Filter getFilter() {
        return getModel().getFilter();
    }

    /**
     * Specifies the filter used for the rule.
     * 
     * @param value
     *            A <code>Filter</code> object that represents the filter of the rule.
     * 
     * @return A <code>Rule</code> object that represents the updated rule.
     */
    public RuleInfo setFilter(Filter value) {
        getModel().setFilter(value);
        return this;
    }

    /**
     * Returns the rule action used for the rule.
     * 
     * @return A <code>RuleAction</code> object that represents the rule action.
     */
    public RuleAction getAction() {
        return getModel().getAction();
    }

    /**
     * Specifies the rule action for the rule.
     * 
     * @param value
     *            A <code>RuleAction</code> object that represents the rule action.
     * 
     * @return A <code>Rule</code> object that represents the updated rule.
     */
    public RuleInfo setAction(RuleAction value) {
        getModel().setAction(value);
        return this;
    }

    public RuleInfo withCorrelationIdFilter(String correlationId) {
        CorrelationFilter filter = new CorrelationFilter();
        filter.setCorrelationId(correlationId);
        return setFilter(filter);
    }

    public RuleInfo withSqlExpressionFilter(String sqlExpression) {
        SqlFilter filter = new SqlFilter();
        filter.setSqlExpression(sqlExpression);
        filter.setCompatibilityLevel(20);
        return setFilter(filter);
    }

    public RuleInfo withTrueFilter() {
        TrueFilter filter = new TrueFilter();
        filter.setCompatibilityLevel(20);
        filter.setSqlExpression("1=1");
        return setFilter(filter);
    }

    public RuleInfo withFalseFilter() {
        FalseFilter filter = new FalseFilter();
        filter.setCompatibilityLevel(20);
        filter.setSqlExpression("1=0");
        return setFilter(filter);
    }

    public RuleInfo withEmptyRuleAction() {
        EmptyRuleAction action = new EmptyRuleAction();
        return setAction(action);
    }

    public RuleInfo withSqlRuleAction(String sqlExpression) {
        SqlRuleAction action = new SqlRuleAction();
        action.setSqlExpression(sqlExpression);
        action.setCompatibilityLevel(20);
        return setAction(action);
    }
}
