package com.reactive.sec09;

import com.reactive.common.Utils;
import com.reactive.sec09.helper.NameGenerator;

public class StartWith {
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();

        nameGenerator
            .generateName()
            .take(2)
            .subscribe(Utils.subscriber("sub1"));
        
        nameGenerator
            .generateName()
            .take(2)
            .subscribe(Utils.subscriber("sub2"));
    
        nameGenerator
            .generateName()
            .take(2)
            .subscribe(Utils.subscriber("sub3"));
    }
}
