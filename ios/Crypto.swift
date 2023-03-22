import CryptoKit
import CryptoSwift

@objc(Crypto)
class Crypto: NSObject {
    
    @objc(blowfishEncrypt:withKey:withResolver:withRejecter:)
    func blowfishEncrypt(
        value: NSString,
        key: NSString,
        resolve: RCTPromiseResolveBlock,
        reject: RCTPromiseRejectBlock
    ) {
        do {
            let plaintext = (value as String).bytes
            let encrypted = try Blowfish(key: (key as String).bytes, blockMode: ECB(), padding: .pkcs7).encrypt(plaintext)
            resolve(encrypted.toHexString())
        } catch {
            resolve(nil)
        }
    }
    
    @objc(blowfishDecrypt:withKey:withResolver:withRejecter:)
    func blowfishDecrypt(
        value: NSString,
        key: NSString,
        resolve: RCTPromiseResolveBlock,
        reject: RCTPromiseRejectBlock
    ) {
        do {
            let plaintext = Array(hex: value as String)
            debugPrint("==== \(plaintext)")
            let decrypted = try Blowfish(key: (key as String).bytes, blockMode: ECB(), padding: .pkcs7).decrypt(plaintext)
            let str = String(bytes: decrypted, encoding: .utf8)!
            debugPrint("====2 \(decrypted)")
            resolve(str)
        } catch {
            resolve(nil)
        }
    }
    
    @objc(sha256Hash:withResolver:withRejecter:)
    func sha256Hash(
        value: NSString,
        resolve: RCTPromiseResolveBlock,
        reject: RCTPromiseRejectBlock
    ) {
        if let d = (value as String).data(using: .utf8) {
            let str = SHA256.hash(data: d).compactMap { String(format: "%02x", $0) }.joined()
            resolve(str)
        } else {
            resolve(nil)
        }
    }
}
