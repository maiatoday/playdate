# Visual Guidelines for Totoro Tribute

## Project Overview
This Kotlin multiplatform project is a visual tribute to the Studio Ghibli film, "My Neighbor Totoro". The project focuses on implementing various visual techniques to create interesting and engaging visual elements that capture the spirit and charm of the beloved animated film.

## Visual Techniques
The project utilizes a variety of Compose visual techniques:

### Canvas and Drawing
- Custom canvas drawing with `drawWithCache` modifier
- Path drawing and transformations
- Brush techniques for different effects
- Linear and radial gradients

### Shapes and Geometry
- RoundedPolygon implementations
- Custom paths and shapes
- Matrix transformations
- Geometric patterns inspired by Totoro's world

### Colors and Styling
- Custom color palettes inspired by the film
- Gradient implementations (linear, radial)
- Color transitions and animations
- Skia shaders for special effects

### Text and Typography
- Custom text rendering
- Font implementations (available in resources folder)
- Text path animations
- Decorative text elements

## Color Palette
The project includes a carefully selected color palette that reflects the film's aesthetic:
```xml
<palette>
    <color name="English Violet" hex="443F5D" r="68" g="63" b="93"/>
    <color name="Orange (Pantone)" hex="FE6515" r="254" g="101" b="21"/>
    <color name="Olivine" hex="94B758" r="148" g="183" b="88"/>
    <color name="Onyx" hex="30333A" r="48" g="51" b="58"/>
    <color name="Baker-Miller pink" hex="FE90B3" r="254" g="144" b="179"/>
    <color name="Night" hex="141417" r="20" g="20" b="23"/>
    <color name="Battleship gray" hex="808584" r="128" g="133" b="132"/>
    <color name="Jasmine" hex="FDE773" r="253" g="231" b="115"/>
    <color name="Platinum" hex="E6E7EA" r="230" g="231" b="234"/>
    <color name="Glaucous" hex="6A84B0" r="106" g="132" b="176"/>
</palette>
```

## Implementation Guidelines
1. All visual elements should be layered on the main composable screen
2. Animations are optional but can be added for enhanced visual appeal
3. Utilize the provided fonts from the resources folder
4. Draw inspiration from materials in:
   - docs/looks folder
   - docs/blogpost folder

## Resources and Inspiration
- Original movie poster and visual references can be found in docs/look folder
- Color palette and style guidelines in docs/look/colors.md
- Additional technical implementations and examples in docs/blog/wrapped2023

## Note
This project is purely for demonstration and creative purposes, focusing on the implementation of visual techniques in Kotlin multiplatform using Compose. It serves as both a technical exercise and an artistic tribute to "My Neighbor Totoro".