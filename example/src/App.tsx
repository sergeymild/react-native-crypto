import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { crypto } from 'react-native-crypto';

export default function App() {
  const [result, setResult] = React.useState<string | undefined>();
  const [result2, setResult2] = React.useState<string | undefined>();

  React.useEffect(() => {}, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Text>Result2: {result2}</Text>

      <TouchableOpacity
        style={{ marginTop: 20 }}
        onPress={() => {
          crypto.blowfish
            .decrypt(
              '1206DF131B6009BEDF74415A8CE0A415',
              'HtHQXuFGCzlOTCbosCKWXQ=='
            )
            .then((r) => {
              setResult(r);
              console.log('[App.]', r);
            });
        }}
      >
        <Text>Encrypt</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={{ marginTop: 20 }}
        onPress={() => {
          crypto.blowfish
            .encrypt(result, 'HtHQXuFGCzlOTCbosCKWXQ==')
            .then(setResult);
        }}
      >
        <Text>Decrypt</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={{ marginTop: 20 }}
        onPress={() => {
          let Amount = '50';
          let Currency = 'EUR';
          let Customer_no = '9562';
          let Payment_id = '99';
          let str =
            Amount +
            Currency +
            Customer_no +
            Payment_id +
            '22-03-2023' +
            'zgZ3gu2rFH5aFZd';

          crypto.sha256.hash(str).then(setResult2);
        }}
      >
        <Text>sha256Hash</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
