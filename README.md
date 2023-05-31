# PASOS
PASOS DE DB2 Y SKUPPER
	1)	Desde la máquina virtual, en el perfil Moni, accedemos a OpenShift, creamos proyecto (bank-infra) e iniciamos Skupper.
	⁃	skupper init --site-name bank-infra --console-auth=internal --console-user=admin --console-password=password
	2)	reboot (cambiar de usuario al db2)
	3)	
	4)	Hacer login en db2inst1
	5)	oc login y oc project bank-infra
	6)	Conectarse a la bbdd en otro terminal db2
	⁃	CONNECT TO OPENBANK USER db2inst1 USING db2inst1
	7)	En el terminal de oc login y oc project
	⁃	skupper gateway init --type podman --namespace bank-infra. (Si da error skipper gateway delete y de nuevo init.)
	8)	skupper gateway expose db 0.0.0.0 25010 --type pod man
	9)	Cojo desde oc get sec, el IP de db y modifico en los application.properties de la aplicación Java por el nuevo IP y subo a git

PASOS TEKTON PIPELINE
	1)	Login al cluster: oc login
	2)	Install Tekton: Operators Hub —>  Openshift Pipelines Operator.
	3)	Create a new project: 
	⁃	oc new-project bank-infra
	4)	Create a service account:
	⁃	oc create serviceaccount pipeline
	⁃	oc adm policy add-sec-to-user privileged -z pipeline
	⁃	oc adm policy add-role-to-user edit -z pipeline
	⁃	oc policy add-role-to-user edit system:serviceaccount:bank-infra:pipeline -n bank-infra
	5)	Install tasks:
	⁃	git clone https://github.com/MonicaGarridoCamacho/bankBackend.git
	⁃	cd bankBackend/pipelines
	⁃	oc apply -f tasks
	6)	Create the pipeline:
	⁃	kubectl apply -f example-bank-pipeline.yaml
	7)	Deploy an instance of sonarqube
	⁃	oc apply -f sonarqube.yaml (user:admin/password:admin). Change password:password
	8)	Create a PVC
	⁃	oc apply -f bank-pvc.yaml
	9)	Run the pipeline
	⁃	Modificar el fichero example-bank-pipeline.yaml y se modifica la url de sonar junto con la nueva password.
	⁃	oc create -f bank-pipeline run.yaml
	⁃	NOTA: Si fallara el análisis de sonarqube de algunas clases es por la versión de sonarqube (modificar sonarqube.yaml para cambiar la versión).
	10)	Cuando se lanza el pipeline y falla en la fase de Quay por la autorización hay que añadir una secret:
	⁃	Start Pipeline: añadir en la URL, la URL de git.
	⁃	En el campo Workspace —> VolumeClaimTemplate.
	⁃	Show Credential options —> Add Secret
	⁃	Secret name: quay-demos
	⁃	Server URL: quay.io
	⁃	Registry server address: quay.io
	⁃	Accedemos a quay.io —> monica_garrido —> Account Settings —> Robot —> View Credentials, copiar username y password y lo añadimos y lo validamos en el check y Start.

VULNERABILITY ANALYSIS (QUAY)
Acceder a Quay.io —> Repositories —> bank-backend —> Tags —> Clicar en el MANIFEST —> Icono de debug (Security Scan).
