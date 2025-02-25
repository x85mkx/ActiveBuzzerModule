package com.gmail.kvederys.mindaugas.ActiveBuzzer;

import com.pi4j.Pi4J; // Import Pi4J library core functionality
import com.pi4j.context.Context; // Import Pi4J context for managing resources
import com.pi4j.io.gpio.digital.DigitalOutput; // Import interface for digital output
import com.pi4j.io.gpio.digital.DigitalState; // Import enum for digital output states (HIGH/LOW)

/**
 * <h2>Active Buzzer Module Test Application</h2>
 * <p>
 * This application demonstrates how to control an active buzzer module using the Pi4J library
 * on a Raspberry Pi or similar single-board computer.
 * Active buzzers produce a sound when a voltage is applied to them, and stop when the voltage is removed.
 * This example makes the buzzer beep a few times to demonstrate basic control.
 * </p>
 * <p>
 * This program initializes the Pi4J context, configures a digital output pin for the active buzzer,
 * and then generates a sequence of beeps by toggling the digital output state.
 * </p>
 * <p>
 * <b>Hardware Setup (Active buzzer module):</b>
 * <ul>
 *     <li><b>GPIO5 [Pin 29]</b> >> I/O (Signal) - Connect to the signal pin (I/O or S) of the active buzzer module.</li>
 *     <li><b>3.3V [Pin 1]</b>  >> POWER (VCC)  - Connect to the power pin (VCC or +) of the active buzzer module to a 3.3V power source on Raspberry Pi.</li>
 *     <li><b>GND [Pin 6]</b>   >> GROUND (GND) - Connect to the ground pin (GND or -) of the active buzzer module to a common ground on Raspberry Pi.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Important Notes:</b>
 * <ul>
 *     <li>Ensure you have correctly wired the active buzzer module to your Raspberry Pi
 *         according to the pinout described above.</li>
 *     <li>Active buzzers typically operate at 3.3V or 5V. This example uses 3.3V. Check the specifications of your buzzer module.</li>
 *     <li>Active buzzers are different from passive buzzers. Active buzzers contain an internal oscillator and produce a tone when voltage is applied.
 *         Passive buzzers require an external oscillating signal to produce sound. This example is for an *active* buzzer.</li>
 *     <li>This example controls the buzzer using GPIO5 (physical pin 29). You can adjust the GPIO pin in the code if needed.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Software Dependencies:</b>
 * This application requires the Pi4J library. Ensure that Pi4J is properly installed and configured in your project.
 * Refer to the Pi4J official documentation for installation instructions and setup guidance.
 * </p>
 */

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Create a new Pi4J context (auto-detects available providers).
        // Pi4J.newAutoContext() initializes Pi4J and automatically selects the best available provider for the current platform.
        Context pi4j = Pi4J.newAutoContext();

        // Build the configuration for a digital output on GPIO5 (address 5).
        // DigitalOutputConfig.Builder is used to configure properties of a digital output pin.
        // The active buzzer is off when the output is HIGH (active low).
        var buzzerConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("buzzer")                      // Assign a unique ID for the buzzer output (optional, but good practice).
                .name("Active Buzzer")            // Assign a descriptive name for the buzzer output.
                .address(5)                     // GPIO5 (physical pin 29) - BCM GPIO numbering is used.
                .shutdown(DigitalState.HIGH)      // Set to HIGH on shutdown (buzzer off).
                                                 // Ensures the buzzer is turned OFF when the program exits.
                .initial(DigitalState.HIGH)       // Start with HIGH (buzzer off).
                                                 // Sets the initial state of the GPIO pin to HIGH, so the buzzer starts in the OFF state.
                .build();                        // Build the DigitalOutputConfig object.

        // Create the digital output instance for the buzzer.
        // pi4j.create(buzzerConfig) creates a DigitalOutput object based on the configuration defined in buzzerConfig.
        DigitalOutput buzzer = pi4j.create(buzzerConfig);

        // Beep 3 times: active buzzer beeps when output is LOW (active low).
        for (int i = 0; i < 3; i++) {
            System.out.println("Beep " + (i + 1)); // Print "Beep" message to the console indicating beep number.
            buzzer.low();    // Turn buzzer ON (active low).
                             // Setting the DigitalOutput to LOW activates the buzzer because it's an active low buzzer.
            Thread.sleep(500); // Beep duration - Pause for 500 milliseconds (0.5 seconds), keeping the buzzer ON.
            buzzer.high();   // Turn buzzer OFF.
                             // Setting the DigitalOutput to HIGH deactivates the buzzer because it's an active low buzzer.
            Thread.sleep(500); // Pause between beeps - Pause for 500 milliseconds (0.5 seconds) of silence between beeps.
        }

        // Clean up and shutdown Pi4J context.
        // It's important to shutdown Pi4J context to release resources when the program finishes.
        pi4j.shutdown(); // Shutdown the Pi4J context, releasing allocated resources and stopping Pi4J services.
    }
}