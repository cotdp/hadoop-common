/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.hadoop.yarn.server.resourcemanager.webapp.dao;

import java.util.Collection;

import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.AppSchedulable;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FairScheduler;
import org.apache.hadoop.yarn.server.resourcemanager.scheduler.fair.FSLeafQueue;

public class FairSchedulerLeafQueueInfo extends FairSchedulerQueueInfo {
  private int numPendingApps;
  private int numActiveApps;
  
  public FairSchedulerLeafQueueInfo(FSLeafQueue queue, FairScheduler scheduler) {
    super(queue, scheduler);
    Collection<AppSchedulable> apps = queue.getAppSchedulables();
    for (AppSchedulable app : apps) {
      if (app.getApp().isPending()) {
        numPendingApps++;
      } else {
        numActiveApps++;
      }
    }
  }
  
  public int getNumActiveApplications() {
    return numPendingApps;
  }
  
  public int getNumPendingApplications() {
    return numActiveApps;
  }
}