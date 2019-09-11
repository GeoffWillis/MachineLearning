#!/usr/bin/env python
# coding: utf-8


import tensorflow as tf
import numpy as np
from tensorflow import keras


def house_model(y_new):
    # Number of Bedrooms, note even though #bedrooms an int, represented as float
    xs = np.array([1.0, 2.0, 3.0, 4.0, 5.0, 6.0], dtype=float)
    
    #Price of house per bedroom, 1 br=100k, 2br=150k, 3br=200k... Note scaled to smaller values
    ys = np.array([1.0, 1.5, 2.0, 2.5, 3.0, 3.5], dtype=float)
    
    #Set up the model with boiler plate code
    model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])
    model.compile(optimizer='sgd', loss='mean_squared_error' )
    
    #We'll play with the number of epocs later
    model.fit(xs, ys, epochs=1000)
    return model.predict(y_new)[0]


def modified_house_model(xs, ys, num_epochs, y_new):
    #Set up the model with boiler plate code
    model = tf.keras.Sequential([keras.layers.Dense(units=1, input_shape=[1])])
    model.compile(optimizer='sgd', loss='mean_squared_error' )
    
    print(f"Fitting the model with {num_epochs} epochs")
    model.fit(xs, ys, epochs=num_epochs)
    tmp = model.predict(y_new)[0]
    temp= tmp[0]
    return model.predict(y_new)[0]

#Original prediction
#prediction = house_model([7.0])
#print(f"Predicted house value for 7 bedrooms: ${prediction*100000}")

# Experiment with num Epochs
xs = np.array([1.0, 2.0, 3.0, 4.0, 5.0, 6.0], dtype=float)
ys = np.array([1.0, 1.5, 2.0, 2.5, 3.0, 3.5], dtype=float)
num_epochs = [10, 100, 500, 1000]
y_new = [7.0]
results = dict()

for epoch in num_epochs:
    mod_prediction = modified_house_model(xs, ys, epoch, y_new )
    print(f"mod_prediction type: {type(mod_prediction[0])}")
    results.setdefault(epoch, {"prediction" :mod_prediction[0]})
    #print(f"Predicted value for {y_new} bedrooms = {mod_prediction}")

print("Debug")

for key, val in results.items():
    print(f"Num Epochs: {key} \t Price: {val.get('prediction')*100000}")


