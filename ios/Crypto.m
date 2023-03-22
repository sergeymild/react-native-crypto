#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Crypto, NSObject)

RCT_EXTERN_METHOD(blowfishEncrypt:(NSString*)value withKey:(NSString*)key
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(blowfishDecrypt:(NSString*)value withKey:(NSString*)key
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(sha256Hash:(NSString*)value
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup {
  return NO;
}

@end
