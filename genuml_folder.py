"""
Generate Class Diagrams in PlantUML from .class files.
"""

import sys
import os
import subprocess
import site
from pathlib import Path

def get_genuml_path():
    """Trouve l'emplacement exact de l'exécutable genuml sur la machine"""
    exe_name = "genuml.exe" if os.name == "nt" else "genuml"
    
    # 1. L'adresse EXACTE basée sur votre ordinateur (Arthur) et votre log d'erreur
    arthur_path = r"C:\Users\arthu\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.11_qbz5n2kfra8p0\LocalCache\local-packages\Python311\Scripts\genuml.exe"
    if os.path.exists(arthur_path):
        return arthur_path

    # 2. Méthode dynamique pour les dossiers Windows Store en général
    store_script_path = os.path.join(os.path.dirname(site.getusersitepackages()), "Scripts", exe_name)
    if os.path.exists(store_script_path):
        return store_script_path
        
    return "genuml"

def main():
    # Vérifier les arguments
    if len(sys.argv) != 2:
        print("Erreur: nombre d'arguments incorrect")
        print("Usage: python genuml_folder.py <directory>")
        sys.exit(1)
    
    directory = sys.argv[1]
    
    # Vérifier que le répertoire existe
    if not os.path.isdir(directory):
        print(f"Erreur: le répertoire '{directory}' n'existe pas")
        sys.exit(1)
    
    # Chercher tous les fichiers .class
    class_files = sorted(Path(directory).rglob("*.class"))
    if not class_files:
        print(f"Erreur: aucun fichier .class trouvé dans '{directory}'")
        sys.exit(1)
    
    print(f"✓ Trouvé {len(class_files)} fichier(s) .class")
    
    # Trouver la commande genuml
    genuml_cmd = get_genuml_path()
    
    # Exécuter genuml sur chaque fichier et combiner les résultats
    all_plantuml_text = []
    failed_files = []
    all_plantuml_text.append("@startuml")
    
    try:
        for class_file in class_files:
            print(f"  → Traitement: {class_file.name}")
            try:
                result = subprocess.run(
                    [genuml_cmd, "generate", str(class_file)],
                    capture_output=True,
                    text=True,
                    check=True
                )
                
                plantuml_text = result.stdout.strip()
                if plantuml_text:
                    all_plantuml_text.append(plantuml_text)
                
            except subprocess.CalledProcessError as e:
                failed_files.append((class_file.name, e.returncode))
                print(f"    ✗ Erreur pour {class_file.name}")
            except FileNotFoundError:
                 print(f"    ✗ Erreur CRITIQUE : l'exécutable genuml.exe est introuvable même avec le chemin forcé.")
                 sys.exit(1)
        
        all_plantuml_text.append("@enduml")

        # Vérifier qu'on a des résultats
        if len(all_plantuml_text) <= 2:
            print("\nErreur: aucun résultat obtenu de genuml")
            sys.exit(1)
        
        # Combiner tous les textes avec une ligne vide entre chaque
        combined_text = "\n".join(all_plantuml_text)
        
        # Écrire dans diagram.plantuml
        output_file = "diagram.plantuml"
        with open(output_file, "w", encoding="utf-8") as f:
            f.write(combined_text)
        
        print(f"\n✓ Fichier généré avec succès: {output_file}")
        print(f"✓ Fichiers traités: {len(all_plantuml_text)-2}/{len(class_files)}")
        
    except Exception as e:
        print(f"Erreur inattendue: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()