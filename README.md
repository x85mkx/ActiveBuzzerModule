## Pi4J Active Buzzer Module Test

This Java application demonstrates how to control an active buzzer module using the Pi4J library on a Raspberry Pi.

**Purpose:**

The `App.java` class provides a simple example of generating sound using an active buzzer module connected to a Raspberry Pi and Pi4J v2. It showcases how to initialize Pi4J, configure a digital output pin to control the buzzer, and create a basic beep sequence. This serves as a beginner-friendly guide for anyone wanting to add simple sound output to their Raspberry Pi projects using an active buzzer and Pi4J.

**Functionality:**

* **Initialization:** Sets up the Pi4J context (`Pi4J.newAutoContext()`), which is the prerequisite for interacting with Raspberry Pi hardware through Pi4J.
* **GPIO Configuration:** Configures a single digital output pin (GPIO 5 in BCM numbering) to control the active buzzer module. It uses the Pi4J `DigitalOutput.Builder` for a clear and structured configuration, setting the initial state to HIGH (buzzer off) and shutdown state to HIGH as well.
* **Beep Sequence:** Generates a sequence of three beeps by:
    * Setting the digital output to `LOW` to turn the active buzzer ON (active low logic).
    * Pausing for 500 milliseconds (beep duration).
    * Setting the digital output to `HIGH` to turn the active buzzer OFF.
    * Pausing for 500 milliseconds (pause between beeps).
* **Active Low Control:** Demonstrates the active low control nature of typical active buzzers, where a `LOW` signal activates the buzzer.
* **Shutdown:** Properly shuts down the Pi4J context (`pi4j.shutdown()`) at the end of the program to release resources.

**Hardware Requirements:**

* **Raspberry Pi:** Any Raspberry Pi model supported by Pi4J.
* **Active Buzzer Module:** A common and inexpensive active buzzer module.
* **Wiring:** Jumper wires to connect the Active Buzzer module to the Raspberry Pi GPIO pins as follows:
    * **I/O (Signal):** GPIO 5 (Pin 29)
    * **POWER (VCC):** 3.3V (Pin 1)
    * **GROUND (GND):** Ground (Pin 6 or any other GND pin)

**Software Requirements:**

* **Java Development Kit (JDK):** Required to compile and run the Java code.
* **Pi4J Library:** This project is built using Pi4J v2. You'll need to include Pi4J as a dependency in your project (e.g., using Maven or Gradle).
* **Operating System:** Raspberry Pi OS (or any OS supported by Pi4J).

**How to Use:**

1. **Clone this repository.**
2. **Ensure you have Pi4J v2 set up in your project.** If you are using Maven, include the Pi4J core dependency in your `pom.xml`.
3. **Compile the `App.java` class.**
4. **Connect the Active Buzzer module to your Raspberry Pi as described above.**
5. **Run the compiled `App` class on your Raspberry Pi.**

**Important Notes:**

* **GPIO Pin Numbering:** The code uses BCM (Broadcom) GPIO pin numbering. Ensure your wiring matches this numbering scheme.
* **Active Buzzer Type:** This code is specifically designed for *active* buzzers. Active buzzers contain an internal oscillator and produce a tone when voltage is applied. Passive buzzers require an external oscillating signal.
* **Active Low Logic:** Active buzzers are often *active low*, meaning they turn ON when their control pin is set to `LOW` and OFF when set to `HIGH`. This code reflects this common behavior.
* **Voltage Compatibility:** This example uses 3.3V for the active buzzer. Ensure your buzzer module is compatible with 3.3V or adjust the power supply accordingly, checking the module's specifications.
* **Permissions:** Ensure that the user running the Java application has the necessary permissions to access the Raspberry Pi's GPIO pins.

**Keywords:** Raspberry Pi, Pi4J, Active Buzzer, Buzzer, Sound, GPIO, Java, IoT, Hardware, Example, Tutorial, Digital Output, Beep, Audio.