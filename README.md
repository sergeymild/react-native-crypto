# react-native-crypto

React native crypto

## Installation

```sh
"react-native-crypto":"sergeymild/react-native-crypto#0.0.1"
```

## Usage

```js
import { crypto } from 'react-native-crypto';

// ...
// decrypt blowfish algorithm (supports only ECB)
await crypto.blowfish.decrypt(encrypted, key)
await crypto.blowfish.encrypt(plainText, key)

// get sha256 hash
await crypto.sha256.hash(string)
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
