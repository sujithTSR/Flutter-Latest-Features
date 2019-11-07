// Imports the Flutter Driver API.
import 'package:flutter_driver/flutter_driver.dart';
import 'package:test/test.dart';

void main() {
  group('Flutter InterOp - Smoke Test', () {
    // First, define the Finders and use them to locate widgets from the
    // test suite. Note: the Strings provided to the `byValueKey` method must
    // be the same as the Strings we used for the Keys in step 1.
    final responseTextFinder = find.byValueKey('__response');
    final buttonFinder = find.byValueKey('__button');

    FlutterDriver driver;

    // Connect to the Flutter driver before running any tests.
    setUpAll(() async {
      driver = await FlutterDriver.connect();
    });

    // Close the connection to the driver after the tests have completed.
    tearDownAll(() async {
      if (driver != null) {
        driver.close();
      }
    });

    test("Before InterOperation", () async{
      expect(await driver.getText(responseTextFinder), "Waiting for the response from Android Code.!");
    });

    test("InterOperation", () async{
      await driver.waitFor(responseTextFinder);
      print("Found the string");

      await driver.tap(buttonFinder);
      expect(await driver.getText(responseTextFinder), "Hello from the Native Code!");
    });

  });
}