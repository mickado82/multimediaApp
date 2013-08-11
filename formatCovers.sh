#!/bin/bash

#bash dedicated to covers conversion to lighter size

find ./ -name "*.jpg" -exec mogrify -resize 128x128 {} \;
find ./ -name "*.jpeg" -exec mogrify -resize 128x128 {} \;
find ./ -name "*.png" -exec mogrify -resize 128x128 {} \;

echo "Conversion done !!"

