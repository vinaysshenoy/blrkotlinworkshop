package com.vinaysshenoy.blrkotlinworkshop.gameoflife

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

class GameOfLifeView(context: Context, attributeSets: AttributeSet?) : View(context, attributeSets) {

  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  @ColorInt
  private val deadColor = Color.WHITE

  @ColorInt
  private val aliveColor = Color.BLACK

  @ColorInt
  private val lineColor = Color.GRAY

  val boardSize = 16

  private val drawRect = RectF()
  private val cellRect = RectF()

  private var cellSize: Float = 0F

  private val lineStart = PointF()
  private val lineEnd = PointF()

  var cells: List<Cell> = emptyList()
    set(value) {
      field = value
      invalidate()
    }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    drawRect.set(0.toFloat(), 0.toFloat(), w.toFloat(), h.toFloat())
    cellSize = w / boardSize.toFloat()
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.drawColor(deadColor)
    drawCells(canvas)
    drawBoard(canvas)
  }

  private fun drawCells(canvas: Canvas) {
    paint.style = Paint.Style.FILL
    paint.color = aliveColor
    cells.filter { it.x < boardSize && it.y < boardSize }
        .filter { it.x >= 0 && it.y >= 0 }
        .filter { it.alive }
        .forEach { cell ->
          val left = cell.x * cellSize
          val top = cell.y * cellSize
          cellRect.set(left, top, left + cellSize, top + cellSize)
          canvas.drawRect(cellRect, paint)
        }
  }

  private fun drawBoard(canvas: Canvas) {
    paint.color = lineColor
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = dpToPx(0.75F)

    for (cellNumber in 0..boardSize) {
      //Draw rows
      lineStart.set(drawRect.left, cellNumber * cellSize)
      lineEnd.set(drawRect.right, cellNumber * cellSize)
      canvas.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y, paint)

      //Draw columns
      lineStart.set(cellNumber * cellSize, drawRect.top)
      lineEnd.set(cellNumber * cellSize, drawRect.bottom)
      canvas.drawLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y, paint)
    }
  }

  private fun dpToPx(dp: Float) = dp * Resources.getSystem().displayMetrics.density

}
