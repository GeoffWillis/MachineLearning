import numpy as np
import pandas as pd
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import cm
import scipy.cluster.hierarchy as sch
from scipy.spatial.distance import cdist
from sklearn.neighbors import NearestNeighbors
import seaborn as sns
sns.set()

# Displays an elbow graph of the data.  From it you should be able to see where the optimal number
# of clusters is.
def elbowNN(X):
    neigh = NearestNeighbors(n_neighbors=2)
    nbrs = neigh.fit(X)
    distances, indices = nbrs.kneighbors(X)
    distances = np.sort(distances, axis=0)
    plt.plot(distances)

