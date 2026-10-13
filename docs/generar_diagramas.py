"""Genera los diagramas PNG del modelo de datos y del ERM del proyecto."""

from pathlib import Path
from typing import Iterable

from PIL import Image, ImageDraw, ImageFont


ANCHO = 2200
ALTO = 1400
FONDO = "#F8FAFC"
TEXTO = "#172554"
SECUNDARIO = "#475569"
LINEA = "#7C3AED"
SALIDA = Path(__file__).resolve().parent


def fuente(tamano: int, negrita: bool = False) -> ImageFont.FreeTypeFont:
    candidatos = [
        Path("C:/Windows/Fonts/seguisb.ttf" if negrita else "C:/Windows/Fonts/segoeui.ttf"),
        Path("/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf" if negrita else "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf"),
    ]
    for candidato in candidatos:
        if candidato.exists():
            return ImageFont.truetype(str(candidato), tamano)
    return ImageFont.load_default()


F_TITULO = fuente(56, True)
F_SUBTITULO = fuente(28)
F_CAJA = fuente(32, True)
F_CAMPO = fuente(25)
F_NOTA = fuente(24)
F_ETIQUETA = fuente(22, True)


def lienzo(titulo: str, subtitulo: str) -> tuple[Image.Image, ImageDraw.ImageDraw]:
    imagen = Image.new("RGB", (ANCHO, ALTO), FONDO)
    dibujo = ImageDraw.Draw(imagen)
    dibujo.text((70, 55), titulo, fill=TEXTO, font=F_TITULO)
    dibujo.text((72, 130), subtitulo, fill=SECUNDARIO, font=F_SUBTITULO)
    return imagen, dibujo


def caja(
    dibujo: ImageDraw.ImageDraw,
    x: int,
    y: int,
    ancho: int,
    titulo: str,
    campos: Iterable[str],
    color: str,
    etiqueta: str | None = None,
) -> tuple[int, int, int, int]:
    campos = list(campos)
    alto = 88 + 50 * len(campos) + 24
    dibujo.rounded_rectangle((x, y, x + ancho, y + alto), radius=22, fill="white", outline=color, width=4)
    dibujo.rounded_rectangle((x, y, x + ancho, y + 72), radius=22, fill=color)
    dibujo.rectangle((x, y + 45, x + ancho, y + 72), fill=color)
    dibujo.text((x + 24, y + 16), titulo, fill="white", font=F_CAJA)
    if etiqueta:
        etiqueta_ancho = dibujo.textbbox((0, 0), etiqueta, font=F_ETIQUETA)[2] + 28
        dibujo.rounded_rectangle(
            (x + ancho - etiqueta_ancho - 18, y + 17, x + ancho - 18, y + 57),
            radius=16,
            fill="#FFFFFF",
        )
        dibujo.text((x + ancho - etiqueta_ancho - 4, y + 24), etiqueta, fill=color, font=F_ETIQUETA)
    for indice, campo in enumerate(campos):
        dibujo.text((x + 28, y + 91 + indice * 50), campo, fill=TEXTO, font=F_CAMPO)
    return x, y, x + ancho, y + alto


def marco_base_datos(dibujo: ImageDraw.ImageDraw, limites: tuple[int, int, int, int], nombre: str) -> None:
    x1, y1, x2, y2 = limites
    dibujo.rounded_rectangle((x1, y1, x2, y2), radius=30, outline="#CBD5E1", width=4)
    dibujo.text((x1 + 22, y1 + 16), nombre, fill=SECUNDARIO, font=F_ETIQUETA)


def generar_modelo_datos() -> None:
    imagen, dibujo = lienzo(
        "Primera evaluación — Modelo de datos",
        "Entidades persistentes de Room y modelos de dominio usados por la aplicación",
    )

    marco_base_datos(dibujo, (65, 205, 1060, 760), "BaseDatosApp · evaluacion_movil_db")
    caja(
        dibujo,
        135,
        275,
        855,
        "EntidadHistorial",
        [
            "id : Int  «PK, autogenerada»",
            "tipo : String",
            "titulo : String",
            "descripcion : String",
            "fecha : Long",
        ],
        "#7C3AED",
        "ROOM",
    )

    marco_base_datos(dibujo, (1140, 205, 2135, 760), "BaseDatosCentavo · módulo de transacciones")
    caja(
        dibujo,
        1210,
        275,
        855,
        "EntidadTransaccion",
        [
            "id : Long  «PK, autogenerada»",
            "titulo : String",
            "montoEnCentavos : Long",
            "tipo : String  «INGRESO | GASTO»",
            "creadaEn : Long",
        ],
        "#0F766E",
        "ROOM",
    )

    dibujo.text((70, 825), "Modelos temporales (no se guardan como tablas)", fill=TEXTO, font=F_CAJA)
    caja(
        dibujo,
        70,
        900,
        620,
        "InformacionDispositivo",
        ["fabricante : String", "modelo : String", "versionAndroid : String", "versionSdk : Int"],
        "#2563EB",
        "DOMINIO",
    )
    caja(
        dibujo,
        790,
        900,
        620,
        "InformacionSensor",
        ["nombre : String", "tipo : Int", "fabricante : String"],
        "#EA580C",
        "DOMINIO",
    )
    caja(
        dibujo,
        1510,
        900,
        620,
        "InformacionUbicacion",
        ["latitud : Double", "longitud : Double"],
        "#DB2777",
        "DOMINIO",
    )

    dibujo.text(
        (72, 1330),
        "Nota: los datos de dispositivo y GPS se transforman en registros de EntidadHistorial.",
        fill=SECUNDARIO,
        font=F_NOTA,
    )
    imagen.save(SALIDA / "modelo-de-datos.png", quality=95)


def generar_erm() -> None:
    imagen, dibujo = lienzo(
        "Primera evaluación — Diagrama entidad–relación (ERM)",
        "Modelo relacional implementado actualmente con Room / SQLite",
    )

    historial = caja(
        dibujo,
        100,
        350,
        720,
        "historial",
        [
            "PK  id : INTEGER",
            "    tipo : TEXT",
            "    titulo : TEXT",
            "    descripcion : TEXT",
            "    fecha : INTEGER",
        ],
        "#7C3AED",
        "TABLA",
    )
    transacciones = caja(
        dibujo,
        1380,
        350,
        720,
        "transacciones",
        [
            "PK  id : INTEGER",
            "    titulo : TEXT",
            "    montoEnCentavos : INTEGER",
            "    tipo : TEXT",
            "    creadaEn : INTEGER",
        ],
        "#0F766E",
        "TABLA",
    )

    y = (historial[1] + historial[3]) // 2
    dibujo.line((historial[2] + 25, y, transacciones[0] - 25, y), fill="#94A3B8", width=4)
    centro = (historial[2] + transacciones[0]) // 2
    dibujo.rounded_rectangle((centro - 245, y - 48, centro + 245, y + 48), radius=20, fill="#F1F5F9")
    dibujo.text((centro - 198, y - 19), "Sin relación / sin clave foránea", fill=SECUNDARIO, font=F_NOTA)

    dibujo.rounded_rectangle((230, 930, 1970, 1235), radius=28, fill="#EEF2FF", outline="#C7D2FE", width=3)
    dibujo.text((275, 975), "Lectura del ERM", fill=TEXTO, font=F_CAJA)
    notas = [
        "• Cada tabla pertenece a una base Room distinta.",
        "• No hay columnas FK ni cardinalidades entre historial y transacciones.",
        "• PK identifica la clave primaria; Room genera el valor del campo id.",
        "• Long de Kotlin se almacena como INTEGER y String como TEXT en SQLite.",
    ]
    for indice, nota in enumerate(notas):
        dibujo.text((285, 1040 + indice * 45), nota, fill=SECUNDARIO, font=F_NOTA)

    imagen.save(SALIDA / "diagrama-entidad-relacion.png", quality=95)


if __name__ == "__main__":
    generar_modelo_datos()
    generar_erm()
    print("Diagramas generados en", SALIDA)
