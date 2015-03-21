//
//  main.m
//  TimeAfterTime
//
//  Created by malus on 2/28/15.
//  Copyright (c) 2015 Big Nerd Ranch. All rights reserved.
//

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[]) {

    @autoreleasepool {
        
        NSHost *host = [NSHost currentHost];
        NSString *string = [host localizedName];
        
        NSLog(@"The address is: %@", string);
        
    
    }
    
    
    return 0;
}
