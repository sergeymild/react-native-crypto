import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-crypto' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const Crypto = NativeModules.Crypto
  ? NativeModules.Crypto
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const blowfish = {
  encrypt(value: string, key: string): Promise<string | undefined> {
    return Crypto.blowfishEncrypt(value, key);
  },

  decrypt(value: string, key: string): Promise<string | undefined> {
    return Crypto.blowfishDecrypt(value, key);
  },
};

const sha256 = {
  hash(value: string): Promise<string | undefined> {
    return Crypto.sha256Hash(value);
  },
};

export const crypto = { blowfish, sha256 };
