/*
 * Copyright (c) 2016, Groupon, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of GROUPON nor the names of its contributors may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/*
 * Changes for TIBCO Project SnappyData data platform.
 *
 * Portions Copyright (c) 2017-2019 TIBCO Software Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

package org.apache.spark.groupon.metrics

import com.codahale.metrics.{Clock, Reservoir}

/**
 * [[MetricMessage]] contains the data and metadata about a metric data point.
 *
 * Each [[MetricMessage]] has the metric value itself and metadata about the type of [[com.codahale.metrics.Metric]]
 * that it belongs to.
 */
sealed trait MetricMessage {
  val metricName: String
  val value: AnyVal
}

private[metrics] case class CounterMessage(
    override val metricName: String,
    override val value: Long) extends MetricMessage

private[metrics] case class GaugeMessage(
    override val metricName: String,
    override val value: AnyVal)
  extends MetricMessage

private[metrics] case class HistogramMessage(
    override val metricName: String,
    override val value: Long,
    reservoirClass: Class[_ <: Reservoir]) extends MetricMessage

private[metrics] case class MeterMessage(
    override val metricName: String,
    override val value: Long)
  extends MetricMessage

private[metrics] case class TimerMessage(
    override val metricName: String,
    override val value: Long,
    reservoirClass: Class[_ <: Reservoir],
    clockClass: Class[_ <: Clock])
  extends MetricMessage

private[metrics] case class Remove(
    override val metricName: String,
    override val value: AnyVal)
  extends MetricMessage

