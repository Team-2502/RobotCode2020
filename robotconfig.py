{
    # Class names of motor controllers used.
    "rightControllerTypes": ["WPI_TalonSRX", "WPI_TalonSRX"],
    "leftControllerTypes": ["WPI_TalonSRX", "WPI_TalonSRX"],

    # Ports for the left-side motors
    "leftMotorPorts": [1, 2],
    # Ports for the right-side motors
    "rightMotorPorts": [5, 6],
    # Inversions for the left-side motors
    "leftMotorsInverted": [True, True],
    # Inversions for the right side motors
    "rightMotorsInverted": [False, False],

    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 0.5,

    # If your robot has only one encoder, remove all of the right encoder fields
    # Encoder pulses-per-revolution (*NOT* cycles per revolution!)
    # This value should be the pulses per revolution *of the wheels*, and so
    # should take into account gearing between the encoder and the wheels
    "encoderPPR": 4096,

    # Whether the left encoder is inverted
    "leftEncoderInverted": False,
    # Whether the right encoder is inverted:
    "rightEncoderInverted": True,

    # Gyro Type
    "gyroType": "NavX",
    # Gyro Port
    "gyroPort": "SPI.Port.kMXP",
}

