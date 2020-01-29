{
    "rightControllerTypes": ["WPI_TalonFX", "WPI_TalonFX"],
    "leftControllerTypes": ["WPI_TalonFX", "WPI_TalonFX"],

    "leftMotorPorts": [3, 4],
    "rightMotorPorts": [1, 2],
    "leftMotorsInverted": [False, False],
    "rightMotorsInverted": [False, False],

    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 0.5,

    # If your robot has only one encoder, remove all of the right encoder fields
    # Encoder pulses-per-revolution (*NOT* cycles per revolution!)
    # This value should be the pulses per revolution *of the wheels*, and so
    # should take into account gearing between the encoder and the wheels
    "encoderPPR": 2048,
    "leftEncoderInverted": False,
    "rightEncoderInverted": False,

    "gyroType": "NavX",
    "gyroPort": "SPI.Port.kMXP",
}

