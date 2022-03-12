

**Solution Presented by Team TechBlitz**


## Problem Statement

**Title** – An All-in-one healthcare platform for your needs, where you are a priority.

**Inspiration** - These pandemic times have taught us the importance of a remote healthcare system. Additionally rural areas currently do not have access to good healthcare systems. This has motivated us to build MedForHer.

## Features
* <b>Breast Cancer Detection tool :</b> Detects the presence of Metastatic Tissue and Invasive Ductal Carcinoma using two AI Models.
* <b>Predict Cervical Cancer</b> and preliminary test results according to user’s demographic information, habits, and historic medical records

## Technology Stack

### Artificial Intelligence & Web Technology

- Machine Learning
  - Python - Sklearn, Keras, Tensorflow etc. for the ML Model(s)
  - Django REST Framework (for Model Endpoints)

- Tools
  - Microsoft Azure Machine Learning Studio for the ML Model(s)
  - Git
  - Github


  ## Machine Learning Tools

### Breast Cancer Prediction Tool

In order to detect cancer, a pathologist examines the slide of tissue under a microscope. S/he visually ensures that there's no cancer and ultimately find malignant areas. We currently provide a tool powered by artifical intelligence which analyses hispathological image i.e. images of glass tissue slides under a microscope, here tissue samples from lymph nodes in order to detect breast cancer. We will be analysing tissues for:

* **Invasive Ductal Carcinoma** - This is the most common subtype of all breast cancers. Almost 80% of diagnosed breast cancers are of this subtype.
* **Metastatic Cancer** - A metastatic cancer or metastatic tumor is one that has spread from the site where it started into different area/s of the body.

We  use the 'Breast Histopathology Images' dataset available at https://www.kaggle.com/c/histopathologic-cancer-detection.

* **IDC_model** 
    * Accuracy: 0.88
    * F1 Score: 0.88

* **Metastatic_model** 
    * Accuracy: 0.94
    * F1 Score: 0.94

## Cervical Cancer Risk Classification Tool
Cervical cancer is a type of cancer that occurs in the cells of the cervix — the lower part of the uterus that connects to the vagina. It isn't clear what causes cervical cancer, but it's certain that HPV plays a role. HPV is very common, and most people with the virus never develop cancer. This means other factors — such as your environment or your lifestyle choices — also determine whether you'll develop cervical cancer.

On the basis of these factors that you may answer about in our form below, we inform you whether you are at risk of cervical cancer or not. We facilitate this by providing predictions for four cervical cancer tests, which may be quite expensive otherwise.

We're using the [UCI's Machine Learning repository's Cervical cancer (Risk Factors) Data Set](https://archive.ics.uci.edu/ml/datasets/Cervical+cancer+%28Risk+Factors%29). The dataset was collected at 'Hospital Universitario de Caracas' in Caracas, Venezuela. The dataset comprises demographic information, habits, and historic medical records.

* **Hinselmann test** - This examines the tissues by illuminating and magnifying is using a colposcope. Our model uses the following attributes

|Category|Value|
|-|-|
|Model|SparseNormalizer, Extreme Random Trees|
|Average Precision Score Weighted|0.94|
|Accuracy|0.93|

* **Schiller test** - Schiller's test or Schiller's Iodine test is a medical test in which iodine solution is applied to the cervix in order to diagnose cervical cancer. Our model uses the following attributes

|Category|Value|
|-|-|
|Model|VotingEnsemble|
|Ensembled Algorithms|'ExtremeRandomTrees', 'KNN', 'RandomForest', 'KNN', 'ExtremeRandomTrees', 'KNN', 'ExtremeRandomTrees'|
|Ensembled Weights|0.1, 0.1, 0.1, 0.2, 0.1, 0.3, 0.1|
|Ensembled Iterations|6, 23, 18, 24, 25, 7, 13|
|Average Precision Score Weighted|0.92|
|Accuracy|0.93|

* **Cytology test** - This is the examination of suspected cells from the body under a microscope. Our model uses the following attributes

|Category|Value|
|-|-|
|Model|VotingEnsemble|
|Ensembled Algorithms|'KNN', 'KNN', 'KNN', 'KNN', 'KNN', 'LightGBM', 'SVM', 'XGBoostClassifier'|
|Ensembled Weights|0.21428571428571427, 0.07142857142857142, 0.14285714285714285, 0.07142857142857142, 0.07142857142857142, 0.21428571428571427, 0.14285714285714285, 0.07142857142857142|
|Ensembled Iterations|66, 61, 68, 59, 73, 41, 27, 50|
|Average Precision Score Weighted|0.92|


* **Biopsy** - This is the final test. The process involves extraction of sample cells or tissues for examination to determine the presence or extent of a disease.Our model uses the following attributes

|Category|Value|
|-|-|
|Model|VotingEnsemble|
|Ensembled Algorithms|'LightGBM', 'RandomForest', 'XGBoostClassifier', 'ExtremeRandomTrees', 'XGBoostClassifier'|
|Ensembled Weights|0.3333333333333333, 0.16666666666666666, 0.16666666666666666, 0.16666666666666666, 0.16666666666666666|
|Ensembled Iterations|0, 14, 15, 6, 26|
|Average Precision Score Weighted|0.90|


## References and Citations

* https://www.researchgate.net/publication/263052166_Automatic_detection_of_invasive_ductal_carcinoma_in_whole_slide_images_with_Convolutional_Neural_Networks
* https://academic.oup.com/gigascience/article/7/6/giy065/5026175
* *B. S. Veeling, J. Linmans, J. Winkens, T. Cohen, M. Welling. "Rotation Equivariant CNNs for Digital Pathology". arXiv:1806.03962
* Ehteshami Bejnordi et al. Diagnostic Assessment of Deep Learning Algorithms for Detection of Lymph Node Metastases in Women With Breast Cancer. JAMA: The Journal of the American Medical Association, 318(22), 2199–2210. doi:jama.2017.14585
* Kelwin Fernandes, Jaime S. Cardoso, and Jessica Fernandes. 'Transfer Learning with Partial Observability Applied to Cervical Cancer Screening.' Iberian Conference on Pattern Recognition and Image Analysis. Springer International Publishing, 2017.
* Andrew Janowczyk and Anant Madabhushi ‘Deep learning for digital pathology image analysis: A comprehensive tutorial with selected use cases’ National Library of Medicine, 2016 Jul 26
* Angel Cruz-Roa, Ajay Basavanhally, Fabio González, Hannah Gilmore, Michael Feldman; Shridar Ganesan, Natalie Shih, John Tomaszewski and Anant Madabhushi. ‘Automatic detection of invasive ductal carcinoma in whole slide images with convolutional neural networks’ SPIE Proceedings Vol. 9041: Medical Imaging 2014: Digital Pathology
* Hannah Gilmore, Fabio A. González, Ajay Basavanhally, Angel Cruz-Roa, ‘Automatic detection of invasive ductal carcinoma in whole slide images with Convolutional Neural Networks’ February 2014 Proceedings of SPIE - The International Society for Optical Engineering 9041


## Team - TechBlitz

| S. No. 	| Name              	| Semester 	| Role                 |
|--------	|-------------------	|----------	|----------------------|
| 1      	| Shruti Rawal      	| 8        	| Android Development|
| 2      	| Sakshi Bhandarkar 	| 8        	| Backend Development |
| 3      	| Anjali Patle  	| 8        	| Full Stack Development        |
| 4      	| Priyanshi Sharma      	| 8        	| Machine Learning   	   |

