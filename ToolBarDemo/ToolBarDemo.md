# ToolBarDemo

#### 继承体系
android.support.v7.widget.Toolbar-->ViewGroup-->View

#### 常用方法
| 方法名称                                     | 描述                                       |
| ---------------------------------------- | ---------------------------------------- |
| setNavigationIcon(@DrawableRes int resId) | Set the icon to use for the toolbar's navigation button.设置导航按钮 |
| setNavigationIcon(@Nullable Drawable icon) | Set the icon to use for the toolbar's navigation button.设置导航按钮 |
| Drawable getNavigationIcon()             | 获取导航按钮                                   |
| setNavigationOnClickListener(OnClickListener listener) | Set a listener to respond to navigation events.导航按钮的事件监听 |
| setLogo(@DrawableRes int resId)          | Set a logo drawable from a resource id.The logo cannot be clicked。 |
| setTitle(CharSequence title)             | Set the title of this toolbar.           |
| setTitleTextColor(@ColorInt int color)   | Sets the text color of the title, if present. |
| setTitleTextAppearance(Context context, @StyleRes int resId) | Sets the text color, size, style, hint color, and highlight color from the specified TextAppearance resource. |
| setSubtitle(CharSequence subtitle)       | Set the subtitle of this toolbar.        |
| setSubtitleTextColor(@ColorInt int color) | Sets the text color of the subtitle, if present. |
| setSubtitleTextAppearance(Context context, @StyleRes int resId) | Sets the text color, size, style, hint color, and highlight color from the specified TextAppearance resource. |
| setOverflowIcon(@Nullable Drawable icon) | Set the icon to use for the overflow button.右上角更多按钮,默认是三个点。 |
| inflateMenu(@MenuRes int resId)          | Inflate a menu resource into this toolbar.设置右上角的更多按钮填充菜单 |
| setOnMenuItemClickListener(OnMenuItemClickListener listener) | Set a listener to respond to menu item click events.右上角的更多按钮填充菜单的点击事件 |
|                                          |                                          |
|                                          |                                          |

