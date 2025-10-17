#!/bin/bash

# Script para hacer shuffle aleatorio de los nombres de committers
# Mantiene todos los demás datos de los commits intactos

echo "Iniciando proceso de shuffle de committers..."

# Lista de autores originales
AUTHORS=("kevinfpuna" "amandacaceres" "majofpuna")

# Crear una copia de seguridad del repositorio
echo "Creando backup del repositorio..."
cp -r .git .git_backup

# Función para generar un shuffle aleatorio de los autores
shuffle_authors() {
    # Crear array con los autores
    local authors=("${AUTHORS[@]}")
    local shuffled=()
    
    # Shuffle usando $RANDOM
    while [ ${#authors[@]} -gt 0 ]; do
        local index=$((RANDOM % ${#authors[@]}))
        shuffled+=("${authors[$index]}")
        # Remover el elemento seleccionado
        authors=("${authors[@]:0:$index}" "${authors[@]:$((index+1))}")
    done
    
    echo "${shuffled[@]}"
}

# Obtener todos los commits en orden cronológico (más antiguo primero)
echo "Obteniendo lista de commits..."
git log --reverse --format="%H" > /tmp/commit_list.txt

# Generar el mapeo de autores aleatorio
echo "Generando mapeo aleatorio de autores..."
shuffled_authors=($(shuffle_authors))

echo "Mapeo generado:"
for i in "${!AUTHORS[@]}"; do
    echo "  ${AUTHORS[$i]} -> ${shuffled_authors[$i]}"
done

# Crear un nuevo branch para el rewrite
echo "Creando nuevo branch para el rewrite..."
git checkout --orphan temp_rewrite

# Contador para el mapeo
author_index=0

# Procesar cada commit
while IFS= read -r commit_hash; do
    if [ -z "$commit_hash" ]; then
        continue
    fi
    
    echo "Procesando commit: $commit_hash"
    
    # Obtener información del commit original
    original_author=$(git show -s --format="%an" $commit_hash)
    original_email=$(git show -s --format="%ae" $commit_hash)
    original_date=$(git show -s --format="%ad" $commit_hash)
    original_message=$(git show -s --format="%s" $commit_hash)
    
    # Encontrar el nuevo autor basado en el mapeo
    new_author=""
    for i in "${!AUTHORS[@]}"; do
        if [ "$original_author" = "${AUTHORS[$i]}" ]; then
            new_author="${shuffled_authors[$i]}"
            break
        fi
    done
    
    # Si no se encuentra el autor, usar el original
    if [ -z "$new_author" ]; then
        new_author="$original_author"
    fi
    
    # Crear el nuevo commit con el autor modificado
    git show $commit_hash --format="" --name-only | while read file; do
        if [ -n "$file" ] && [ -f "$file" ]; then
            git show $commit_hash:$file > "$file" 2>/dev/null || true
        fi
    done
    
    # Agregar todos los archivos
    git add -A
    
    # Crear el commit con el nuevo autor
    GIT_AUTHOR_NAME="$new_author" \
    GIT_AUTHOR_EMAIL="$original_email" \
    GIT_AUTHOR_DATE="$original_date" \
    GIT_COMMITTER_NAME="$new_author" \
    GIT_COMMITTER_EMAIL="$original_email" \
    GIT_COMMITTER_DATE="$original_date" \
    git commit -m "$original_message" --date="$original_date"
    
    author_index=$((author_index + 1))
    
done < /tmp/commit_list.txt

echo "Rewrite completado!"
echo "Verificando el resultado..."

# Mostrar algunos commits del resultado
echo "Primeros 10 commits después del shuffle:"
git log --oneline --format="%H %an %ae %ad %s" | head -10

echo ""
echo "¿Deseas aplicar estos cambios al branch principal? (y/n)"
read -r response

if [ "$response" = "y" ] || [ "$response" = "Y" ]; then
    echo "Aplicando cambios al branch principal..."
    
    # Volver al branch principal
    git checkout main
    
    # Hacer reset al nuevo historial
    git reset --hard temp_rewrite
    
    # Limpiar
    git branch -D temp_rewrite
    rm -f /tmp/commit_list.txt
    
    echo "¡Cambios aplicados exitosamente!"
    echo "El historial de commits ahora tiene autores aleatorios."
else
    echo "Cambios descartados. El branch temp_rewrite contiene los cambios."
    echo "Para aplicarlos manualmente:"
    echo "  git checkout temp_rewrite"
    echo "  git checkout main"
    echo "  git reset --hard temp_rewrite"
fi

echo "Script completado."
