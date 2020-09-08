package com.reese.fsd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FSDUsers {

    private final Map<String, FSDUserInfo> connectedUsers = Collections.synchronizedMap(new HashMap<>());

}
