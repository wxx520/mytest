package com.wxxtest.rpc.registration.center.client;

import java.util.Map;

public interface RPCServiceFindService {

  Map<String,Integer> findService(String serviceName);
}
