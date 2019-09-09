import numpy as np
import pandas as pd
import matplotlib
import matplotlib.pyplot as plt
from matplotlib import cm
import scipy.cluster.hierarchy as sch
from scipy.spatial.distance import cdist
from sklearn.cluster import KMeans
from sklearn.cluster import AgglomerativeClustering
from sklearn.metrics import silhouette_samples, silhouette_score

# Displays an elbow graph of the data.  From it you should be able to see where the optimal number
# of clusters is.
def elbow(X):
    distortions = []
    K = range (1, 36)
    for k in K:
        kmeanModel = AgglomerativeClustering(n_clusters=k, affinity='euclidean', linkage='ward').fit(X)
        kmeanModel.fit(X)
        distortions.append(sum(np.min(cdist(X, kmeanModel.cluster_centers_, 'euclidean'), axis=1)) / X.shape[0])
               
    plt.plot(K, distortions, 'bx-')
    plt.xlabel('k')
    plt.ylabel('Distortion')
    plt.title("The Elbow Method showing the optimal k")
    plt.show()
    print("leaving elbow")
    

# Display a silhouette graph of the data.  It is supposed to help you learn where the optimal number of
# clusters is.
def silhouette(X):
    
    range_n_clusters = [11,12,13,14,15,16,17,18,19,20,21,22,23,24]
    for n_clusters in range_n_clusters:
        # Create a subplot with 1 row and 2 columns
        fig, (ax1, ax2) = plt.subplots(1,2)
        fig.set_size_inches(18,7)
        
        # The 1st subplot is the silhouette plot
        # The silhouette coefficient can range from -1, 1 but in this xaple all
        # lie within 
        ax1.set_xlim([-1, 1])
        
#        ax1.set_ylim([(0, X[:,0] + n_clusters + 1) * 10])
              
        clusterer = KMeans(n_clusters=n_clusters, random_state=0)
        cluster_labels = clusterer.fit_predict(X)
 
        silhouette_avg = silhouette_score(X, cluster_labels)
        print("For n-clusers =", n_clusters,"The average silhouette_score is :", silhouette_avg)
        
        sample_silhouette_values = silhouette_samples(X, cluster_labels, metric='euclidean')
        y_lower = 10
        yticks = []
        for i in range(n_clusters):
            ith_cluster_silhouette_values = sample_silhouette_values[cluster_labels ==i]
            ith_cluster_silhouette_values.sort()
            size_cluster_i = ith_cluster_silhouette_values.shape[0]
            y_upper  = y_lower + size_cluster_i
            color = cm.nipy_spectral(float(i) / n_clusters)
            ax1.fill_betweenx(np.arange(y_lower, y_upper),
                         0, ith_cluster_silhouette_values,
                         facecolor=color, edgecolor=color, alpha=0.7)
            ax1.text(-0.05,y_lower + 0.5 * size_cluster_i, str(i))
            
            y_lower = y_upper + 10
            
        ax1.set_title("The silhouette plot for the various lusters.")
        ax1.set_xlabel("The silhouete coefficien values")
        ax1.set_ylabel("Cluster label")
        
        ax1.set_yticks([])
        ax1.set_xticks([-1.0, -.8, -.6, -.4, -.2, 0, 0.2, 0.4, 0.6, 0.8, 1])
        colors = cm.nipy_spectral(cluster_labels.astype(float) / n_clusters)
        ax2.scatter(X[:, 0], X[:, 1], marker = '.', s=30, lw=0, alpha=0.7,
                                              c=colors, edgecolor='k')
        
        centers = clusterer.cluster_centers_
        
        ax2.scatter(centers[:, 0], centers[:,1], marker='o',c="white", alpha=1, s=200, edgecolor = 'k')
        for i, c in enumerate(centers):
            ax2.scatter(c[0], c[1], marker='$%d$' %i, alpha=1,s=50, edgecolor='k')
            
        ax2.set_title("The visualization of the clustered data.")
        ax2.set_xlabel("Feature space for the 1st feature")
        ax2.set_ylabel("Feature space for the 2nd feature")
        
        plt.suptitle(("Silhouette analysis for KMeans lusteringon sample data "
                     "with N_clusters = %d" % n_clusters),fontsize=14, fontweight = 'bold')

    plt.show()