/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.connector.calendar;

import java.util.Map;

import io.syndesis.integration.component.proxy.ComponentProxyComponent;
import io.syndesis.integration.component.proxy.ComponentProxyCustomizer;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

import com.google.api.services.calendar.model.Event;

public class GoogleCalendarEventsCustomizer implements ComponentProxyCustomizer {

    @Override
    public void customize(ComponentProxyComponent component, Map<String, Object> options) {
        component.setBeforeConsumer(GoogleCalendarEventsCustomizer::beforeConsumer);
    }

    static void beforeConsumer(Exchange exchange) {
        final Message in = exchange.getIn();
        final Event event = exchange.getIn().getBody(Event.class);

        GoogleCalendarEventModel model = GoogleCalendarEventModel.newFrom(event);
        in.setBody(model);
    }
}
